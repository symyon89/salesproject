package ItemOptions;

import BussinessExceptions.InvaildEndDateException;

import java.time.LocalDate;

public class Price {
    private double price;
    private double discountedPrice;
    private LocalDate startDate;
    private LocalDate endDate;


    public Price setPrice(double price, double discountedPrice, LocalDate startDate, LocalDate endDate) throws InvaildEndDateException {
        checkDate(startDate,endDate);
        this.price = price;
        this.discountedPrice = discountedPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        return this;
    }

    public Price setPrice(double price) {
        this.price = price;
        return this;
    }

    public double getPrice() {
        return checkPrice();
    }

    private void checkDate(LocalDate startDate, LocalDate endDate) throws InvaildEndDateException {
        if (startDate.isAfter(endDate)){
            throw new InvaildEndDateException();
        }
    }

    private double checkPrice() {
        if(discountedPrice == 0){
            return price;
        } else {
            return checkPriceByDate();
        }
    }

    private double checkPriceByDate() {
        if (LocalDate.now().isBefore(startDate)){
            return price;
        }
        if (LocalDate.now().isBefore(endDate)){
            return price;
        }
        return discountedPrice;
    }

}
