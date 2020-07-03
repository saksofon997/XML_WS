package rental.soap.arrays;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "errorFault2", propOrder = {
        "error",
        "time"
})
public class ErrorResponse {
    @XmlElement
    private String error;

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    private Date time;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
/* getters/setters */
