
package jhonatastomaz.bot.api.database.jhonatas;

/**
 * @author JH�NATAS
 *
 */
public class Dados {

	private TiposDados tipoprimitivo;
	private Object objeto;
	
	public Dados(TiposDados tipoprimitivo, Object objeto) {
		this.tipoprimitivo = tipoprimitivo;
		this.objeto = objeto;
	}

	public TiposDados getTipoprimitivo() {
		return tipoprimitivo;
	}

	public void setTipoprimitivo(TiposDados tipoprimitivo) {
		this.tipoprimitivo = tipoprimitivo;
	}

	public Object getObjeto() {
		return objeto;
	}

	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}
	
	
	
	
}
