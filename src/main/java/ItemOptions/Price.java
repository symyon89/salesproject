package ItemOptions;

import BussinessExceptions.InvaildEndDateException;
import BussinessExceptions.InvalidPriceException;

import java.time.LocalDate;

public class Price {
    private double price;
    private double discountedPrice;
    private LocalDate startDate;
    private LocalDate endDate;


    public Price setPrice(double price, double discountedPrice, LocalDate startDate, LocalDate endDate) throws InvaildEndDateException, InvalidPriceException {
        checkDate(startDate,endDate);
        checkIfPriceIsGreaterThanZero(price);
        checkIfPriceIsGreaterThanZero(discountedPrice);
        this.price = price;
        this.discountedPrice = discountedPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        return this;
    }

    public Price setPrice(double price) throws InvalidPriceException {
        checkIfPriceIsGreaterThanZero(price);
        this.price = price;
        return this;
    }

    public double getPrice() {
        return checkIfHasDiscountedPrice();
    }

    public String getPriceToWriteOnFile(){
        if (discountedPrice == 0){
            return String.valueOf(this.price);
        }
        return this.price + "," + this.discountedPrice + "," + this.startDate + "," + this.endDate;

    }

    private void checkDate(LocalDate startDate, LocalDate endDate) throws InvaildEndDateException {
        if (startDate.isAfter(endDate)){
            throw new InvaildEndDateException();
        }
    }

    private void checkIfPriceIsGreaterThanZero(double price) throws InvalidPriceException {
        if (price < 0){
            throw new InvalidPriceException();
        }
    }

    private double checkIfHasDiscountedPrice() {
        if(discountedPrice == 0){
            return this.price;
        } else {
            return checkPriceByDate();
        }
    }

    private double checkPriceByDate() {
        if (LocalDate.now().isBefore(startDate)){
            return this.price;
        }
        if (LocalDate.now().isBefore(endDate)){
            return this.price;
        }
        return this.discountedPrice;
    }


}
