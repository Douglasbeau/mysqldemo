# mysqldemo
start with springboot app with mysql
<p>Bean生命周期
<p>Bean factory implementations should support the standard bean lifecycle interfaces
as far as possible. The full set of initialization methods and their standard order is:

  1. BeanNameAware's {@code setBeanName}
  2. BeanClassLoaderAware's {@code setBeanClassLoader}
  3. BeanFactoryAware's {@code setBeanFactory}
  4. EnvironmentAware's {@code setEnvironment}
  5. EmbeddedValueResolverAware's {@code setEmbeddedValueResolver}
  6. ResourceLoaderAware's {@code setResourceLoader}(only applicable when running in an application context)
  7. ApplicationEventPublisherAware's {@code setApplicationEventPublisher}(only applicable when running in an application context)
  8. MessageSourceAware's {@code setMessageSource}(only applicable when running in an application context)
  9. ApplicationContextAware's {@code setApplicationContext}(only applicable when running in an application context)
  10. ServletContextAware's {@code setServletContext}(only applicable when running in a web application context)
  11. {@code `postProcessBeforeInitialization`} methods of BeanPostProcessors
  13. InitializingBean's {@code **`afterPropertiesSet`**}
  14. a custom **init-method** definition
  15. {@code `postProcessAfterInitialization`} methods of BeanPostProcessors

<p>On shutdown of a bean factory, the following lifecycle methods apply:  

  - {@code postProcessBeforeDestruction} methods of DestructionAwareBeanPostProcessors
  -  style="red">DisposableBean's {@code destroy}
  - a custom destroy-method definition

bean创建之前，有一些bean先创建了，比如BeanFactoryPostProcessor 
bean的创建：
1. doCreateBean 
-> 实例化 -> 内部beanDefinition postprocessor处理一下mbd -> addSingletonFactory registeredSingletons
-> 初始化:
  poupulate -> 
   1. InstantiationAwareBeanPostProcessor: before/postProcessAfterInstantiation, postProcessProperties， 内部使用，如setter
   2. PropertyValies中，进行依赖的autowire
   3. 可以applyPropertyValues
  initializeBean ->
   1. setXXAware() for Aware的bean
   2. BeanPostProcessor `beforeInitialization` datasource 的setter在此调用
   3. invokeInitMethod: afterPropertiesSet() & init-method，或者@PostConstruct
   4. BeanPostProcessor `afterInitialization`
-> 获取单例，从singletomFactories移除，ealySingletonObjects加入。处理依赖

