package rental.dto;

public enum RentalStatus {
    CANCELED, PENDING, RESERVED, FINISHED;

    public static RentalStatus findByName(String status){
        for(RentalStatus s : values()){
            if( s.name().equals(status)){
                return s;
            }
        }
        return null;
    }
}
