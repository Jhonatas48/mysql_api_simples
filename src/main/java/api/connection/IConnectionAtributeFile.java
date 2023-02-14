/**
 * 
 */
package api.connection;

import java.io.File;

/**
 * @author Cristiano TI
 *
 */
public interface IConnectionAtributeFile extends IConnectionAtributes {

	public void setNameFile(String name);

	public void setFile(File file);

	public File getFile();

}
