
package jhonatastomaz.bot.api.database.jhonatas;

/**
 * @author JH�NATAS
 *
 */
public class Transformador {
		
		
		private static Object objeto;
		private int id;
		private Object ob;
		public Transformador(Object obj) {
			objeto=obj;
			
		}
		
		public Transformador() {
			
			
		}
		public Transformador(Object o,int id) {
			this.ob=o;
			this.id=id;
			
		}
		public Float toFloat() {
			boolean checknulo = objeto == null;
			if (checknulo) {
				return null;
			}
			float valor = 0f;
			try {
				valor = Float.parseFloat(String.valueOf(objeto));
			} catch (Exception e) {
				System.out.println("Nao foi possivel transformar o objeto para float");
				e.printStackTrace();
			}
			return valor;
		}

		public Long toLong() {

			boolean checknulo = objeto == null;
			if (checknulo) {
				return null;
			}
			long valor = 0l;
			try {
				valor = Long.parseLong(String.valueOf(objeto));
			} catch (Exception e) {
				System.out.println("Nao foi possivel transformar o objeto para long");
				e.printStackTrace();
			}
			return valor;
		}

		public Integer toInteger() {

			boolean checknulo = objeto == null;
			if (checknulo) {
				return null;
			}
			int valor = 0;
			try {
				valor = Integer.parseInt(String.valueOf(objeto));
			} catch (Exception e) {
				System.out.println("Nao foi possivel transformar o objeto para Integer");
				e.printStackTrace();
			}
			return valor;
		}
		
		public Short toShort() {

			boolean checknulo = objeto == null;
			if (checknulo) {
				return null;
			}
			short valor = 0;
			try {
				valor = Short.parseShort(String.valueOf(objeto));
			} catch (Exception e) {
				System.out.println("Nao foi possivel transformar o objeto para Integer");
				e.printStackTrace();
			}
			return valor;
		}
		public Double toDouble() {
			boolean checknulo = objeto == null;
			if (checknulo) {
				return null;
			}
			double valor = 0;
			try {
				valor = Double.parseDouble(String.valueOf(objeto));
			} catch (Exception e) {
				System.out.println("Nao foi possivel transformar o objeto para Integer");
				e.printStackTrace();
			}
			return valor;
		}
		
		public String toString() {

			boolean checknulo = objeto == null;
			if (checknulo) {
				return null;
			}
			String valor = "";
			try {
				valor = String.valueOf(objeto);
			} catch (Exception e) {
				System.out.println("Nao foi possivel transformar o objeto para String");
				e.printStackTrace();
			}
			return valor;
		}

		public Boolean toBoolean() {

			boolean checknulo = objeto == null;
			if (checknulo) {
				return null;
			}
			boolean valor = false;
			try {
				valor = Boolean.parseBoolean(String.valueOf(objeto));
			} catch (Exception e) {
			System.out.println("Nao foi possivel transformar o objeto para Boolean");
				e.printStackTrace();
			}
			return valor;
		}

		public static Object getObjeto() {
			return objeto;
		}

		public static void setObjeto(Object objeto) {
			Transformador.objeto = objeto;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public Object getOb() {
			return ob;
		}

		public void setOb(Object ob) {
			this.ob = ob;
		}

		
		
		
		
	}