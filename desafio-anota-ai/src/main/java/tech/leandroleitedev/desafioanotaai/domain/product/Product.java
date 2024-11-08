package tech.leandroleitedev.desafioanotaai.domain.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.json.JsonObject;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tech.leandroleitedev.desafioanotaai.domain.category.Category;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "products")
public class Product {

    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;
    private Integer price;
    private String categoryId;

    public Product(ProductDto productDto) {
        this.title = productDto.title();
        this.description = productDto.description();
        this.ownerId = productDto.ownerId();
        this.price = productDto.price();
        this.categoryId = productDto.categoryId();

    }

    @Override
    public String toString() {
        var json = new JSONObject();
        json.put("title", this.title);
        json.put("description", this.description);
        json.put("ownerID", this.ownerId);
        json.put("id", this.id);
        json.put("categoryId", this.categoryId);
        json.put("price", this.price);
        json.put("type", "product");
        return json.toString();
    }
}
