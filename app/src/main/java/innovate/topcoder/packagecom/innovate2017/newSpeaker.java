package innovate.topcoder.packagecom.innovate2017;

/**
 * Created by Aurorayqz on 2017/9/9.
 */

public class newSpeaker {
    private String name;
    private String detail;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private int imageId;
    public newSpeaker(String name,String title,String detail,int imageId){
        this.name=name;
        this.title=title;
        this.detail=detail;
        this.imageId=imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
