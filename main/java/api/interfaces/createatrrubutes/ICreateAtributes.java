package api.interfaces.createatrrubutes;

import api.models.enums.CreateAtributes;

@SuppressWarnings("rawtypes")
public interface ICreateAtributes<T extends ICreateAtributes> {

	public T getAtribute();
	public CreateAtributes getType();
}
