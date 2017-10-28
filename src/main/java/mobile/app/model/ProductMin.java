package mobile.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "product")
public class ProductMin {

    @Id
    private String id;
    private String name;
    private String brand;
    private List<String> images;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public ProductMin() {
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String toString() {
        return "ID: " + getId() + "\n"
                + "Name: " +getName() + "\n"
                + "Brand: " +getBrand() + "\n";
    }

    public static ProductMin parseProduct(Product product) {
        ProductMin productMin = new ProductMin();
        productMin.setId(product.getId());
        productMin.setName(product.getName());
        productMin.setBrand(product.getBrand());
        productMin.setImages(product.getImages());
        return productMin;
    }

}
