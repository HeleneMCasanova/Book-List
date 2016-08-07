import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Books {

	private ImageView image;
	private String bookName;
	private String bookAuthor;
	private String writing;
	private String genre;
	private String path;
	
	public Books(ImageView i, String bookn, String booka, String view, String g, String p)
	{
		
		bookName = bookn;
		bookAuthor = booka;
		writing = view;
		image = i;
		genre = g;
		path = p;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public void setPath(String p)
	{
		path = p;
	}
	
	public String getGenre()
	{
		return genre;
	}
	
	public void setGenre(String g)
	{
		genre = g;
	}
	
	public String getBookName()
	{
		return bookName;
	}
	public void setBookName(String s)
	{
		bookName = s;
	}
	
	public String getBookAuthor()
	{
		return bookAuthor;
	}
	public void setBookAuthor(String s)
	{
		bookAuthor = s;
	}
	
	public void setReview(String s)
	{
		writing = s;
	}
	public String getReview()
	{
		return writing;
	}
	 public void setImage(ImageView value) {
	        image = value;
    }

	public ImageView getImage() {
	        return image;
	    }
}
