package de.stetro.booking.application.ui.hoteldetails;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.stetro.booking.application.MainApplication;
import de.stetro.booking.application.R;
import de.stetro.booking.application.data.Hotel;
import de.stetro.booking.application.ui.hotel.HotelPresenter;
import de.stetro.booking.application.ui.hotel.HotelView;

/**
 * Created by chperich on 10/14/2016.
 */

public class HotelDetailsActivity extends AppCompatActivity implements HotelView {

    @Inject
    public HotelPresenter hotelPresenter;

    @BindView(R.id.hotel_description_image)
    public ImageView hotelImage;

    @BindView(R.id.hotel_description_lines)
    public TextView description;

    @BindView(R.id.hotel_description_address)
    public TextView address;

    @BindView(R.id.hotel_description_phone)
    public TextView phone;

    @BindView(R.id.hotel_description_price)
    public TextView price;

    @BindView(R.id.hotel_description_star)
    public RatingBar stars;

    @BindView(R.id.hotel_description_rating)
    public TextView rating;

    @BindView(R.id.hotel_description_location)
    public TextView location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainApplication.getApplicationComponent(this).inject(this);
        setContentView(R.layout.activity_hotel_details);
        setTitle("Hotel Details");
        ButterKnife.bind(this);
        hotelPresenter.setView(this);
    }

    @Override
    public void setState(List<Hotel> hotels, Integer selectedIndex) {
        Hotel selectedHotel  = hotels.get(selectedIndex);
        setTitle(selectedHotel.getName());
        stars.setRating(selectedHotel.getStars());
        location.setText(selectedHotel.getLocation());
        rating.setText(String.valueOf(selectedHotel.getRating()));
        price.setText(String.format(Locale.UK, "%d €", selectedHotel.getPrice()));
        address.setText(selectedHotel.getAddress());
        description.setText(selectedHotel.getDesc());
        phone.setText(selectedHotel.getNumber());

        Glide
                .with(this)
                .load(selectedHotel.getThumbnailUrl())
                .asBitmap()
                .centerCrop()
                .into(hotelImage);
    }

    @Override
    public void setLoading(boolean isLoading) {

    }

    @Override
    public Context getContext() {
        return this;
    }
}