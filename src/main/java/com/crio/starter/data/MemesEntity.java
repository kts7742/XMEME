package com.crio.starter.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.mongodb.lang.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "memes")
@NoArgsConstructor
public class MemesEntity {

    @Id
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private String url;

    @NonNull
    private String caption;

    public Integer getId(){
      return this.id;
    }
    public void setId(int id){
      this.id =  id;
    }
    public void setName(String name){
      this.name =  name;
    }
    public void setUrl(String url){
      this.url =  url;
    }
    public void setCaption(String caption){
      this.caption =  caption;
    }
  // private String extId;

  // private String message;

  // public String getExtId() {
  //   return extId;
  // }

  // public void setExtId(String extId) {
  //   this.extId = extId;
  // }

  // public String getMessage() {
  //   return message;
  // }

  // public void setMessage(String message) {
  //   this.message = message;
  // }

}
