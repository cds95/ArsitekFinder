package Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlob;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.microsoft.azure.storage.blob.ListBlobItem;

/**
 * Sets the file settings, and destinations of the various files to be saved in the system
 * @author christopherdimitrisastropranoto
 *
 */
public class FileManager {
	public static final String AZUREHOST = "https://jhstudio.blob.core.windows.net";
	public static final String NAME = System.getenv("AZURE_NAME");
	public static final String KEY = System.getenv("AZURE_KEY");
	public static final String storageConnectionString =
		    "DefaultEndpointsProtocol=http;" +
		    "AccountName=jhstudio;" +
		    "AccountKey=" + KEY;  
	
	private CloudBlobContainer container;
	public FileManager() {
		try
		{
		    CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);
		    CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
		    this.container = blobClient.getContainerReference("user-info");
		    this.container.createIfNotExists();
		}
		catch (Exception e)
		{
		    // Output the stack trace.
		    e.printStackTrace();
		}
	}
	
	/**
	 * Uploads the passed in FileItem into the azure cloud storage and returns the blobs
	 * url name
	 * @param file
	 * @throws Exception
	 */
	public String uploadToAzure(FileItem file) throws Exception {
		String name = file.getName();
		CloudBlockBlob blob = this.container.getBlockBlobReference(name);
		File source = new File(name);
		file.write(source);
		blob.upload(new FileInputStream(source), source.length());
		return AZUREHOST + blob.getUri().getPath();
	}
	
	/**
	 * Searches through the container and downloads a file to the target path if the 
	 * target file path is found
	 * @param targetPath
	 * @param target
	 * @throws FileNotFoundException
	 * @throws StorageException
	 */
	public void downloadFromAzure(String targetPath, String target) throws FileNotFoundException, StorageException {
	    for (ListBlobItem blobItem : container.listBlobs()) {
	        if (blobItem instanceof CloudBlob) {
	        	CloudBlob blob = (CloudBlob) blobItem;
	        	String blobName = blob.getName();
	            if(target.equals(blobName)) {
	            	 blob.download(new FileOutputStream(targetPath + blobName));
	            }
	        }
	    }
	}
	
	/**
	 * Checks to see whether or not file name contains a space.  Returns true if there is a space
	 * and false otherwise
	 * @param fileName
	 * @return
	 */
	public static boolean containsSpace(String fileName) {
		for(int i = 0; i < fileName.length(); i++) {
			char let = fileName.charAt(i);
			if(let == ' ') {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Formats a string with a gap to be suitable for URL
	 * @param fileName
	 * @return
	 */
	public static String formatter(String fileName) {
		String res = "";
		for(int i = 0; i < fileName.length(); i++) {
			char letter = fileName.charAt(i);
			if(letter == ' ') {
				res += "%20";
			} else {
				res += letter;
			}	
		}
		return res;
	}
	
}
