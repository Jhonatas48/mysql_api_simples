package api.connection.impl.atributes;

import java.io.File;
import java.io.IOException;

import api.connection.connectionatributes.IConnectionAtributeFile;

public abstract class ConnectionAtributesFiles implements IConnectionAtributeFile {

	private File file;
	private String nameFile;
	
	private void createFile() throws Exception {
		
		if(nameFile ==null) {
			
			throw new NullPointerException("File Name is null");
		}
		if(nameFile.isEmpty()) {
			
			throw new NullPointerException("File Name is null");
		}
		
		if(!file.exists()) {
			//file.mkdir();
			
			try {
				file.createNewFile();
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		}
		//System.out.println("PAth: "+file.getAbsolutePath());
	
	}
	
	
	public String getAtributesConnectionFiles() {
		
		try {
			createFile();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return ":"+getFile();
	}

	@Override
	public void setNameFile(String name) {
		
		this.nameFile= name;
		file = new File(name);
		nameFile= file.getName();
	}
	
	@Override
	public void setFile(File file) {
		
		this.file=file;
		
	}

	@Override
	public File getFile() {
		
		return this.file;
	}


}
