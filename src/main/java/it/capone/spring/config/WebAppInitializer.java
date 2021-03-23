package it.capone.spring.config;

//public class WebAppInitializer implements WebApplicationInitializer {
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//	AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
//	appContext.register(WebMvcConfig.class);
//
//	ServletRegistration.Dynamic dispatcher = servletContext.addServlet("SpringDispatcher",
//		new DispatcherServlet(appContext));
//	dispatcher.setLoadOnStartup(1);
//	dispatcher.addMapping("/");
//
//    }
//}