/**
 * 
 */
package api.connection.connectionatributes;

import java.io.File;

import api.interfaces.connection.connectionatributes.IConnectionAtributes;

/**
 * @author Jhonatas
 *
 */
public interface IConnectionAtributeFile extends IConnectionAtributes {

	public void setNameFile(String name);

	public void setFile(File file);

	public File getFile();

}
