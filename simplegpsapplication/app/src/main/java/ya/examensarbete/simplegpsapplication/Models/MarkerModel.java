package ya.examensarbete.simplegpsapplication.Models;


public class MarkerModel {
    private int markerID;
    private String markerAddress;
    private String markerLocation;
    private String markerLatitude;
    private String markerLongitude;
    private String snippetTag;
    private String created;
    private String lastUpdated;
    private boolean isActive;



    public MarkerModel(int markerID, String markerAddress, String markerLocation, String markerLatitude, String markerLongitude, String snippetTag, String created, String lastUpdated, boolean isActive) {
        this.markerID = markerID;
        this.markerAddress = markerAddress;
        this.markerLocation = markerLocation;
        this.markerLatitude = markerLatitude;
        this.markerLongitude = markerLongitude;
        this.snippetTag = snippetTag;
        this.created = created;
        this.lastUpdated = lastUpdated;
        this.isActive = isActive;
    }

    public MarkerModel(String markerAddress, String markerLocation, String markerLatitude, String markerLongitude, String snippetTag, String created, String lastUpdated, boolean isActive) {
        this.markerAddress = markerAddress;
        this.markerLocation = markerLocation;
        this.markerLatitude = markerLatitude;
        this.markerLongitude = markerLongitude;
        this.snippetTag = snippetTag;
        this.created = created;
        this.lastUpdated = lastUpdated;
        this.isActive = isActive;
    }

    public MarkerModel() {
    }

    public String getMarkerAddress() {
        return markerAddress;
    }

    public void setMarkerAddress(String markerAddress) {
        this.markerAddress = markerAddress;
    }

    public String getMarkerLatitude() {
        return markerLatitude;
    }

    public void setMarkerLatitude(String markerLatitude) {
        this.markerLatitude = markerLatitude;
    }

    public String getMarkerLongitude() {
        return markerLongitude;
    }

    public void setMarkerLongitude(String markerLongitude) {
        this.markerLongitude = markerLongitude;
    }
    public int getMarkerID() {
        return markerID;
    }

    public void setMarkerID(int markerID) {
        this.markerID = markerID;
    }

    public String getMarkerLocation() {
        return markerLocation;
    }

    public void setMarkerLocation(String markerLocation) {
        this.markerLocation = markerLocation;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getSnippetTag() {
        return snippetTag;
    }

    public void setSnippetTag(String snippetTag) {
        this.snippetTag = snippetTag;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "MarkerModel{" +
                "markerID=" + markerID +
                ", markerAddress='" + markerAddress + '\'' +
                ", markerLocation='" + markerLocation + '\'' +
                ", markerLatitude='" + markerLatitude + '\'' +
                ", markerLongitude='" + markerLongitude + '\'' +
                ", snippetTag='" + snippetTag + '\'' +
                ", created='" + created + '\'' +
                ", lastUpdated='" + lastUpdated + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
