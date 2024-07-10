package fr.eni.tp.encheres.bll;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import fr.eni.tp.encheres.bo.Article;
import fr.eni.tp.encheres.dal.ArticleDAO;

@Service
public class FileServiceImpl implements FileService {
	
	@Value("${upload.path}") //Affecte la valeur égale à celle dans app.properties
	private String uploadPath;
	
	private ArticleDAO articleDAO;
	
	public FileServiceImpl(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}

	@Override
	public void saveFile(MultipartFile file, Article article) throws IOException {
        if (file.isEmpty()) {
           String NonUniqueFilename = "placeholderImage.jpg";
           affectAndUpdateArticle(article, NonUniqueFilename);
        }else {
        	
	        // Créer le répertoire de sauvegarde s'il n'existe pas
	        File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdirs();
	        }
	
	        // Générer un nom de fichier unique
	        String originalFilename = file.getOriginalFilename();
	        String extension = "";
	        if(originalFilename != null && originalFilename.contains(".")) {
	        	// On récupère l'extension du fichier (jpg, jpeg, png etc....)
	        	extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
	        }
	        String uniqueFilename = UUID.randomUUID().toString() + extension;
	        Path destinationFile = Paths.get(uploadDir.getAbsolutePath(), uniqueFilename).normalize();
	
	        // Copier le fichier uploadé dans le répertoire de destination
	        Files.copy(file.getInputStream(), destinationFile);
	
	        affectAndUpdateArticle(article, uniqueFilename);
	        
        }
        
    }
	
	private void affectAndUpdateArticle(Article article, String uniqueFilename) {
		article.setImageUUID(uniqueFilename);
		
		//Il faut modifer UPDATE dans la DAO
		articleDAO.updateArticle(article);
	}
	
	/*
	@Override
	public Resource loadFileAsResource(String filename) {
		 try {
	            Path filePath = Paths.get(uploadPath).resolve(filename).normalize();
	            Resource resource = new UrlResource(filePath.toUri());
	            System.err.println(resource);
	            
	            if (resource.exists() || resource.isReadable()) {
	                return resource;
	            } else {
	                throw new RuntimeException("Could not read file: " + filename);
	            }
	        } catch (Exception e) {
	            throw new RuntimeException("Could not read file: " + filename, e);
	        }
	}
	*/
	
	

}
