package api.connection.impl.atributes;

import java.io.File;
import java.io.IOException;

import api.connection.connectionatributes.IConnectionAtributeFile;

public abstract class ConnectionAtributesFiles implements IConnectionAtributeFile {

	private File file;
	private String nameFile;
	
	private void createFile() throws Exception {
		System.out.println("Criando arquivo");
		if(nameFile ==null) {
			
			throw new NullPointerException("File Name is null");
		}
		System.out.println("Verificando seo nome é nulo");
		if(nameFile.isEmpty()) {
			
			throw new NullPointerException("File Name is null");
		}
		System.out.println("Verificando se o arquivo existe");
		if(!file.exists()) {
			//file.mkdir();
			
			try {
				System.out.println("Criando arquivo");
				file.createNewFile();
				System.out.println("Arquivo criado");
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		}
		//System.out.println("PAth: "+file.getAbsolutePath());
	
	}
	
	
	public String getAtributesConnectionFiles() {
		System.out.println("Checkando arquivo");
		try {
			System.out.println(".....................");
			createFile();
			System.out.println("...........");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		System.out.println("retornando o arquivo");
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
