package api.exception.annotations;

public class AnnotationNotFoundException   extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3023226142104327630L;


	static Throwable t = new Throwable("AnnotationNotFoundException");
	 
	 public AnnotationNotFoundException(){
		 
		  super("Annotation Not Found in the class");
	    }
	 
	 public AnnotationNotFoundException(String annotation,String classz) {
		 super("Annotation "+annotation+ " not found in class "+classz);
	 }
	 public  AnnotationNotFoundException(String message) {
			super(message);
		
		}
	
}
