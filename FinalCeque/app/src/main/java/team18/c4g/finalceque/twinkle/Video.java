package team18.c4g.finalceque.twinkle;

/
 * Created by team18.c4g.finalceque.twinkle dhanak on 7/30/2017.
 */

public class Video
{
    int id,user_id,  timestamp,lp_id;
    String youtube_id ;

    public Video()
    {

    }

    public Video(int id)
    {
        this.id = id;
    }

    public Video(int id, int user_id, String youtube_id , int timestamp , int lp_id)
    {
        super();
        this.user_id = user_id;
        this.youtube_id = youtube_id;
        this.timestamp = timestamp;
       this.lp_id = lp_id;
    }

    public int getId()
    {
        return id;
    }

    public int getUser_id()
    {
        return user_id;
    }

    public String getYoutube_id()
    {
        return youtube_id;
    }

    public int getIp_id()
    {
        return lp_id;
    }
    public int getTimestamp()
    {
        return timestamp;
    }



}
