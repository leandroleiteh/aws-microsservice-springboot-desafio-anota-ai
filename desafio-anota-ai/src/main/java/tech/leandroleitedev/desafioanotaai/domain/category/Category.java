package tech.leandroleitedev.desafioanotaai.domain.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tech.leandroleitedev.desafioanotaai.domain.product.ProductDto;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "categories")
public class Category {

    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;

    public Category(CategoryDto categoryDto){
        this.title = categoryDto.title();
        this.description = categoryDto.description();
        this.ownerId = categoryDto.ownerId();
    }

    @Override
    public String toString() {
        var json = new JSONObject();
        json.put("title", this.title);
        json.put("description", this.description);
        json.put("ownerID", this.ownerId);
        json.put("id", this.id);
        json.put("type", "categoty");
        return json.toString();
    }
}
