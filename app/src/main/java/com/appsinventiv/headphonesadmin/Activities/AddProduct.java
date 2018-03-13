package com.appsinventiv.headphonesadmin.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.appsinventiv.headphonesadmin.Adapters.SelectedImagesAdapter;
import com.appsinventiv.headphonesadmin.Models.PictureModel;
import com.appsinventiv.headphonesadmin.Models.ProductModel;
import com.appsinventiv.headphonesadmin.Models.SelectedAdImages;
import com.appsinventiv.headphonesadmin.R;
import com.appsinventiv.headphonesadmin.Utils.GifSizeFilter;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;

import java.util.ArrayList;
import java.util.List;

public class AddProduct extends AppCompatActivity {
    ArrayList<SelectedAdImages> selectedAdImages;
    SelectedImagesAdapter adapter;
    Button pick, upload,pickThumbnail;
    EditText et_title, et_price, et_subtitle, et_description, et_specs, et_sku, et_quantity, et_weight, et_color;
    private static final int REQUEST_CODE_CHOOSE_THUMBNAIL = 22;
    private static final int REQUEST_CODE_CHOOSE = 23;

    List<Uri> mSelected;
    List<Uri> thumbnailUriList;
    StorageReference mStorageRef;
    DatabaseReference mDatabase;
    ImageView thumbnailImage;
    String productId;
    int categoryPicked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        upload = findViewById(R.id.upload);
        pickThumbnail=findViewById(R.id.pick_thumbnail);
        thumbnailImage=findViewById(R.id.photo_thumbnail);
        et_title = findViewById(R.id.title);
        et_price = findViewById(R.id.order_price);
        et_subtitle = findViewById(R.id.subtitle);
        et_description = findViewById(R.id.description);
        et_specs = findViewById(R.id.specs);
        et_sku = findViewById(R.id.sku);
        et_quantity = findViewById(R.id.quantity);

        et_color = findViewById(R.id.color);
        pick = findViewById(R.id.pick);

        mStorageRef = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        final String[] items = new String[] {"Bluetooth","Headphones","Handfree","Ear buds","Wired Handfree","Speakers"};
        Spinner spinner = (Spinner) findViewById(R.id.t5);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                items[0] = "Bluetooth";
//                 items[position];
                categoryPicked =position;
//                Toast.makeText(Filters.this, ""+items[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        pickThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                thumbnailUriList.clear();
                Matisse.from(AddProduct.this)
                        .choose(MimeType.allOf())
                        .countable(true)
                        .maxSelectable(1)
                        .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                        .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f)
                        .imageEngine(new GlideEngine())
                        .forResult(REQUEST_CODE_CHOOSE_THUMBNAIL);
            }
        });

        pick.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                selectedAdImages.clear();
                Matisse.from(AddProduct.this)
                        .choose(MimeType.allOf())
                        .countable(true)
                        .maxSelectable(10)
                        .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                        .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f)
                        .imageEngine(new GlideEngine())
                        .forResult(REQUEST_CODE_CHOOSE);

            }
        });
        showPickedPictures();
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_title.getText().toString().length() == 0) {
                    et_title.setError("Cannot be null");
                } else if (et_price.getText().toString().length() == 0) {
                    et_price.setError("Cannot be null");
                } else if (et_subtitle.getText().toString().length() == 0) {
                    et_subtitle.setError("Cannot be null");
                } else if (et_description.getText().toString().length() == 0) {
                    et_description.setError("Cannot be null");
                }  else if (et_sku.getText().toString().length() == 0) {
                    et_sku.setError("Cannot be null");
                } else if (et_quantity.getText().toString().length() == 0) {
                    et_quantity.setError("Cannot be null");
                } else if (et_color.getText().toString().length() == 0) {
                    et_color.setError("Cannot be null");
                } else if (et_specs.getText().toString().length() == 0) {
                    et_specs.setError("Cannot be null");
                } else {
                    String id, itemTitle, subTitle, itemDescription, itemSpecification, itemColor, thumbnailUrl, sku, isActive;
                    long itemPrice, itemQuantity;


                    itemTitle = et_title.getText().toString();
                    itemPrice = Long.parseLong(et_price.getText().toString());
                    subTitle = et_subtitle.getText().toString();
                    itemDescription = et_description.getText().toString();
                    itemSpecification = et_specs.getText().toString();
                    sku = et_sku.getText().toString();
                    itemQuantity = Long.parseLong(et_quantity.getText().toString());

                    itemColor = et_color.getText().toString();

//                    Toast.makeText(AddProduct.this, "Correct", Toast.LENGTH_SHORT).show();

                     productId= mDatabase.push().getKey();
                    long time = System.currentTimeMillis();
                    mDatabase.child("products").child(productId).setValue(new ProductModel(productId, itemTitle, subTitle, itemPrice, itemDescription, itemSpecification, "", itemColor,sku, "yes", time,itemQuantity,categoryPicked));
                    int number=0;
                    for (Uri img : mSelected
                            ) {
                        putPictures(img,""+productId,number);
                        number++;


                    }
                    putThumbnail(thumbnailUriList.get(0),productId);
                    
                    Intent i =new Intent(AddProduct.this,Done.class);
                    startActivity(i);
//
                }
            }
        });
    }


    public void putPictures(Uri path, final String key, final int number) {
//         file = data.getData();
        String imgName = Long.toHexString(Double.doubleToLongBits(Math.random()));

        ;
//        Uri file = Uri.fromFile(path);


        StorageReference riversRef = mStorageRef.child("Photos").child(imgName);

        riversRef.putFile(path)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    @SuppressWarnings("VisibleForTests")
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content

                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
//                        Toast.makeText(AddProduct.this, "" + downloadUrl, Toast.LENGTH_SHORT).show();
                        mDatabase.child("products").child(key).child("images").push().setValue(new PictureModel(""+downloadUrl,number));

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                        Toast.makeText(AddProduct.this, "error", Toast.LENGTH_SHORT).show();
                    }
                });


    }
    public void putThumbnail(Uri path, final String key) {

        String imgName = Long.toHexString(Double.doubleToLongBits(Math.random()));
        StorageReference riversRef = mStorageRef.child("Photos").child(imgName);
        riversRef.putFile(path)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    @SuppressWarnings("VisibleForTests")
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content

                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
//                        Toast.makeText(AddProduct.this, "" + downloadUrl, Toast.LENGTH_SHORT).show();
                        mDatabase.child("products").child(key).child("thumbnailUrl").setValue(""+downloadUrl);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                        Toast.makeText(AddProduct.this, "error", Toast.LENGTH_SHORT).show();
                    }
                });


    }


    private void showPickedPictures() {
        selectedAdImages = new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(AddProduct.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);
        adapter = new SelectedImagesAdapter(AddProduct.this, selectedAdImages);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CODE_CHOOSE_THUMBNAIL && resultCode==RESULT_OK){
            thumbnailUriList=Matisse.obtainResult(data);
//            Toast.makeText(this, ""+thumbnailUriList, Toast.LENGTH_SHORT).show();
            Glide.with(this).load(thumbnailUriList.get(0)).into(thumbnailImage);
        }
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);

            adapter.notifyDataSetChanged();
            for (Uri img :
                    mSelected) {
                selectedAdImages.add(new SelectedAdImages("" + img));
                adapter.notifyDataSetChanged();

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
