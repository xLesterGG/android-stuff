/**
 * Created by lesgo on 10/25/2016.
 */
public class ProfileName {
    private String mName;
    private String mNationality;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmNationality() {
        return mNationality;
    }

    public void setmNationality(String mNationality) {
        this.mNationality = mNationality;
    }

    public ProfileName(String mName, String mNationality) {

        this.mName = mName;
        this.mNationality = mNationality;
    }
}
