package team18..ceque_app;

/
 * Created by twinkle dhanak on 7/29/2017.
 */

public class LessonPlan
{
    int id,user_id, topic_id , timestamp;
    String description , imageurl;



    public LessonPlan(int id)
    {
        this.id = id;
    }

    public LessonPlan(int id, int user_id, int topic_id , int timestamp , String description , String imageurl)
    {
        super();
        this.user_id = user_id;
        this.topic_id = topic_id;
        this.timestamp = timestamp;
        this.description = description;
        this.imageurl = imageurl;
    }

    public int getId()
    {
        return id;
    }

    public int getUser_id()
    {
        return  user_id;
    }
    public int getTopic_id()
    {
        return topic_id;
    }
    public int getTimestamp()
    {
        return timestamp;
    }
    public String getDescription()
    {
        return description;
    }
    public String getImageurl()

    {
        return imageurl;
    }

}
