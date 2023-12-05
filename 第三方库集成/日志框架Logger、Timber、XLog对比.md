### 一、为什么需要日志框架？

原生 logcat 的痛点：

* 日志不能持久化，缓冲区日志很容易丢失
* 如果系统压力大有可能会导致日志折叠、丢失
* 无法定义日志输出格式，如：json、xml
* 无法快速定位日志输出时的代码位置

### 二、期望的日志框架能力

* 方案轻量，入侵度越低越好
* 集成方便、快捷，不应该在集成日志库上花费太多时间
* 日志留存，至少可以存储到本地磁盘
* 文件策略管理，处理文件备份、删除等策略
* 输出格式规整，最好能自定义输出格式
* 方便筛选，可根据日志级别、标签筛选
* 代码位置定位，可从 IDE 直接跳到相关代码位置
* 配置丰富，给开发者高度的自由

> 在日志框架里，性能是一个伪需求。因为多数日志在正式版本上并不会打印，正式版本打印的都是比较少的关键日志。而且，在 Android 里，我们提到的性能指标一般都是针对主线程，因为主线程的性能会反馈到界面上，能让用户感知到，所以主线程的工作效率一定要高。而耗时操作，比如说这里的日志 IO 操作，其实都在子线程执行，不会影响主线程，只要性能不要太差就行。

### 三、Github 上高星日志框架对比

1. [Logger](https://github.com/orhanobut/logger)

   Logger 是比较早期的一个日志框架，积累到现在的人气超高，拥有将近 14K 的 Star。这个库非常轻量，只有 13 个类，你敢信！？

   集成比较简单，只需要指定输出日志的 Adapter：

   ```java
   FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
     .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
     .methodCount(0)         // (Optional) How many method line to show. Default 2
     .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
     .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
     .tag("My custom tag")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
     .build();

   Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
   ```

   ```java
   FormatStrategy formatStrategy = CsvFormatStrategy.newBuilder()
     .tag("custom")
     .build();

   Logger.addLogAdapter(new DiskLogAdapter(formatStrategy));
   ```

   ![img](https://github.com/orhanobut/logger/raw/master/art/logger_output.png "Output")

   日志输出格式还是挺好看的，可以直接输出 Collection、json、xml 类型数据，但是不能自定义输出格式。 日志可以保存到磁盘，但不能配置文件相关策略（文件名、备份、删除等），可以理解为，有存储文件功能，但不多。

   这个框架可以满足我们基本的日志需求，将日志保存到文件，且不会丢失。日志输出格式也还不错。但相对而言，对于个性化的支持就比较欠缺了。比如，输出格式是不能简单自定义的，如果只想输出一行日志，不输出表格线，那就会比较麻烦。
2. [Timber](https://github.com/JakeWharton/timber)

   Timber 是 Jake Wharton 大神出品。Timber 与其他日志库不太一样的是它并没有提供很多功能，而是搭建了一个日志功能框架，大家可以按照自己的需求来构建自己的 `Tree`。

   Timber 比 Logger 更简单，只有一个类文件，使用 Kotlin 语言。不过这些代码主要是框架代码，只有一个实现类 `DebugTree` 用来实现原生控制台输出日志, **可以自已自定义输出格式，可以不用指定 TAG，默认 TAG 为类名** ，来看看如何使用。

   ```java
   if (BuildConfig.DEBUG) {
      Timber.plant(new DebugTree());
   } else {
      Timber.plant(new FileLoggingTree());
   }
   ```

   ```java
   private static class FileLoggingTree extends Timber.Tree {
           @Override
           protected void log(int priority, String tag, String message, Throwable t) {
               if (TextUtils.isEmpty(CacheDiaPath)) {
                   return;
               }
               File file = new File(CacheDiaPath + "/log.txt");
               Log.v("dyp", "file.path:" + file.getAbsolutePath() + ",message:" + message);
               FileWriter writer = null;
               BufferedWriter bufferedWriter = null;
               try {
                   writer = new FileWriter(file);
                   bufferedWriter = new BufferedWriter(writer);
                   bufferedWriter.write(message);
                   bufferedWriter.flush();

               } catch (IOException e) {
                   Log.v("dyp", "存储文件失败");
                   e.printStackTrace();
               } finally {
                   if (bufferedWriter != null) {
                       try {
                           bufferedWriter.close();
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   }
               }
           }
   }
   ```

   其中 `String CacheDiaPath = context.getCacheDir().toString();`

   一个很优秀的日志框架，如果你需要输出到文件、云端，那可以定义自己的 `FileLoggingTree`、`CloudFileTree`, 然后初始化时用 `Timber.plant()`方法，把自定义的 Tree "种植"下去就行。

   它可以将日志多种输出形式集中成 `Tree`，通过 `Forest `统一管理。
3. [XLog](https://github.com/elvishew/xLog)

   轻量、美观、强大、可扩展的 Android 和 Java 日志库，可同时将日志打印在如 Logcat、Console 和文件中。如果你愿意，你可以将日志打印到任何地方。

   ![img](https://github.com/elvishew/XLog/raw/master/images/logcat-output.png "Logcat Output")

   跟 Logger 挺像的，不同的是 XLog 可以自定义输出格式，Logger 不行。就比如这些花里胡哨的 boder ，在 XLog 里可以方便配置。

   ```java
   LogConfiguration config = new LogConfiguration.Builder()
       .logLevel(BuildConfig.DEBUG ? LogLevel.ALL             // Specify log level, logs below this level won't be printed, default: LogLevel.ALL
           : LogLevel.NONE)
       .tag("MY_TAG")                                         // Specify TAG, default: "X-LOG"
       .enableThreadInfo()                                    // Enable thread info, disabled by default
       .enableStackTrace(2)                                   // Enable stack trace info with depth 2, disabled by default
       .enableBorder()                                        // Enable border, disabled by default
       .jsonFormatter(new MyJsonFormatter())                  // Default: DefaultJsonFormatter
       .xmlFormatter(new MyXmlFormatter())                    // Default: DefaultXmlFormatter
       .throwableFormatter(new MyThrowableFormatter())        // Default: DefaultThrowableFormatter
       .threadFormatter(new MyThreadFormatter())              // Default: DefaultThreadFormatter
       .stackTraceFormatter(new MyStackTraceFormatter())      // Default: DefaultStackTraceFormatter
       .borderFormatter(new MyBoardFormatter())               // Default: DefaultBorderFormatter
       .addObjectFormatter(AnyClass.class,                    // Add formatter for specific class of object
           new AnyClassObjectFormatter())                     // Use Object.toString() by default
       .addInterceptor(new BlacklistTagsFilterInterceptor(    // Add blacklist tags filter
           "blacklist1", "blacklist2", "blacklist3"))
       .addInterceptor(new MyInterceptor())                   // Add other log interceptor
       .build();

   Printer androidPrinter = new AndroidPrinter(true);         // Printer that print the log using android.util.Log
   Printer consolePrinter = new ConsolePrinter();             // Printer that print the log to console using System.out
   Printer filePrinter = new FilePrinter                      // Printer that print(save) the log to file
       .Builder("<path-to-logs-dir>")                         // Specify the directory path of log file(s)
       .fileNameGenerator(new DateFileNameGenerator())        // Default: ChangelessFileNameGenerator("log")
       .backupStrategy(new NeverBackupStrategy())             // Default: FileSizeBackupStrategy(1024 * 1024)
       .cleanStrategy(new FileLastModifiedCleanStrategy(MAX_TIME))     // Default: NeverCleanStrategy()
       .flattener(new MyFlattener())                          // Default: DefaultFlattener
       .writer(new MyWriter())                                // Default: SimpleWriter
       .build();

   XLog.init(                                                 // Initialize XLog
       config,                                                // Specify the log configuration, if not specified, will use new LogConfiguration.Builder().build()
       androidPrinter,                                        // Specify printers, if no printer is specified, AndroidPrinter(for Android)/ConsolePrinter(for java) will be used.
       consolePrinter,
       filePrinter);
   ```

   如果不想自定义配置，只需要 `XLog.init(LogLevel.ALL);`即可完成初始化。

   XLog 的架构思想与 Timber 差不多是一致的，日志框架基于功能接口管理所有日志输出，框架本身的日志输出实现一样是基于框架定义的接口。不同的是，XLog 接口定义得更细致。同时，框架本身也有所有接口的全部默认实现，这些实现就已经可以满足部分开发者。如果不满足，可以基于框架定义自己的实现。
4. 框架对比

| 日志框架 | Stars | 轻量指数 | 集成成本 | 日志存储 | 日志管理策略 | 输出格式规整 | 方便筛选 | 代码位置定位 | 配置灵活度 |
| :------: | :---: | :-------: | :-------: | :------: | :----------: | :----------: | :------: | :----------: | :--------: |
|  Logger  | 13.7K | * * * * * |   * * *   |   * *   |      ×      |    * * *    |    √    |      √      |     *     |
|  Timber  | 10.2K | * * * * * | * * * * * |    ×    |      ×      |   * * * *   |    √    |      ×      |   * * *   |
|   XLog   |  3K  |  * * * *  |  * * * *  |    √    |  * * * * *  |  * * * * *  |    √    |      √      | * * * * * |
