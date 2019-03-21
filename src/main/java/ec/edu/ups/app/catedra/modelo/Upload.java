package ec.edu.ups.app.catedra.modelo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FilenameUtils;//paara sacar l extension de los Archivos: FileNameUtils.getextension
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

public class Upload {
	private List<String> rutasHtml;
	/**
	 * Metodo que sube el objeto de aprendizaje completo al servidor.
	 * @param context Es el contexto de la palicaicon en ese momento.
	 * @param files Son los archivos a subir en el servidor.
	 * @return Retorna un listado con la ruta en la que se subio el objeto de aprendizaje en el servidor,
	 * asi como tambien los archivos que contiene.
	 */
	/*public List<Object> subirOA(FacesContext context, List<Part> files) {
		File oA=new File(context.getExternalContext().getRealPath("/uploads"));//se va a cargar en la carpeta uploads con el path completo desde /home
		//crea el directorio para el OA
		crearDirectorios(oA.getAbsolutePath());
		//VariablesEstaticas.incrementaF=10;
		//VariablesEstaticas.incrementaFA=5;
		//crea los archivos dentro del directorio del OA
		String pathOA="";
		rutasHtml=new ArrayList<String>();
		//rutasJS=new ArrayList<String>();
		//rutasCSS=new ArrayList<String>();
		List<String> rutasHTML=new ArrayList<String>();
		int cont=0;
		if(files!=null) {//si el listado de archivos es diferente de null
			for(Part file: files) {
				pathOA=oA+"/"+file.getSubmittedFileName().substring(0, file.getSubmittedFileName().indexOf('/'));//genera el path del nuevo objeto de aprendizaje en el servidor
				String padre=oA+"/"+file.getSubmittedFileName().substring(0, file.getSubmittedFileName().lastIndexOf("/"));//ruta del directorio principal del oa en el servidor
				System.out.println("Directorio: "+padre+" nombre submited: "+file.getSubmittedFileName());
				crearDirectorios(padre);//crea el directorio principal en el servidor
				rutasHTML=escribir(oA, file); //crea los archovos y directorios dentro del directorio padre
				cont++;
				int resto=files.size()-cont;
				//VariablesEstaticas.incrementaF=(cont*100)/files.size();
				//VariablesEstaticas.incrementaFA=(cont*30)/files.size();
				//System.out.println(VariablesEstaticas.incrementaF);
			}
		}
		System.out.println("Tamano: "+rutasHTML.size());
		if(!pathOA.equals("")&&rutasHTML!=null) {
			List<Object> retorna=new ArrayList<Object>();
			retorna.add(oA);//ruta de todos los oAs en formato File
			retorna.add(pathOA);//ruta del objeto de aprendizaje en formato String
			retorna.add(rutasHTML);//todas las rutasde los html del oa en formato List
			//retorna.add(rutasJS);//todas las rutas de los js
			//retorna.add(rutasCSS);//todas las rutas de los css
			return retorna;//retorno la lista con el dorectorio de todos los OAs, con la ruta del OA y con las rutas de los html
		}
		return null;
	}
	
	/**
	 * Metodo para crear directorios a partir de un path
	 * @param padre path que indica donde se creara el directorio
	 */
	/*public void crearDirectorios(String padre) {
		try {
			File directories=new File(padre);
			if(!directories.exists()) {//si no existen los direcotrios
				directories.mkdirs();//crea todos los directorios en jerarquia
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Metodo que escribe archivos en una o varias rutas
	 * @param oA Es el direcotrio padre de los oas
	 * @param f es el archivo que se va a escribir
	 */
	/*public List<String> escribir(File oA, Part  f) {
		try {
			InputStream input=f.getInputStream();
			long size=f.getSize();
			byte [] buffer=new byte[(int)size];
			input.read(buffer);
			
			//escribe
			String pathcompleto=oA+"/"+f.getSubmittedFileName();//ruta completa donde se va escribir el archivo
			File archivo=new File(pathcompleto);
			OutputStream output=new FileOutputStream(archivo);
			output.write(buffer);//escribe el archivo en la ruta
			output.close();
			
			if(FilenameUtils.getExtension(archivo.getName()).equals("html")||FilenameUtils.getExtension(archivo.getName()).equals("htm")) {//extrae la extension del nombre del archivo y verifca si es igual a html o htm
				rutasHtml.add(pathcompleto);//agrego la ruta completa del archivo html o htm al listado
			}
			/*if(FilenameUtils.getExtension(archivo.getName()).equals("js")) {//extrae la extension del nombre del archivo y verifca si es igual a js(javascript)
				rutasJS.add(pathcompleto);//agrego la ruta completa del archivo js al listado
			}
			if(FilenameUtils.getExtension(archivo.getName()).equals("css")) {//extrae la extension del nombre del archivo y verifca si es igual a css
				rutasCSS.add(pathcompleto);//agrego la ruta completa del archivo css al listado
			}
			//System.out.println("Este es el nombre del archivo "+archivo.getName()+" extension: "+FilenameUtils.getExtension(archivo.getName())+" tamanio "+rutasHtml.size());
		return rutasHtml;	
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/
}
