package wuguanglei.exam.com.wuguanglei.bean;

/**
 * Created by admin on 2016/6/25.
 */
public class ExamEntity {
    private String id;
    private String title;
    private String pic_min;
    private String nickname;

    public ExamEntity(String id, String title, String pic_min, String nickname) {
        this.id = id;
        this.title = title;
        this.pic_min = pic_min;
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPic_min() {
        return pic_min;
    }

    public void setPic_min(String pic_min) {
        this.pic_min = pic_min;
    }
}
