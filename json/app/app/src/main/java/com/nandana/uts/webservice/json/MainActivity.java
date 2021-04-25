package com.nandana.uts.webservice.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MainActivity extends Activity implements OnClickListener {
    Kopi kopi = new Kopi();
    TableLayout kopiTable;

    Button btnAddKopi;
    ArrayList<Button> btnEdit = new ArrayList<>();
    ArrayList<Button> btnDelete = new ArrayList<>();

    JSONArray arrayKopi;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        kopiTable = findViewById(R.id.tabKopi);
        btnAddKopi = findViewById(R.id.btnAddItem);
        btnAddKopi.setOnClickListener(this);

        TableRow tableRow = new TableRow(this);
        tableRow.setBackgroundColor(Color.CYAN);

        TextView viewHeaderId = new TextView(this);
        TextView viewHeaderBrand = new TextView(this);
        TextView viewHeaderRasa = new TextView(this);
        TextView viewHeaderStok = new TextView(this);
        TextView viewHeaderHarga = new TextView(this);
        TextView viewHeaderAction = new TextView(this);

        viewHeaderId.setText("ID");
        viewHeaderBrand.setText("Brand");
        viewHeaderRasa.setText("Rasa");
        viewHeaderStok.setText("Stok");
        viewHeaderHarga.setText("Harga");
        viewHeaderAction.setText("Action");

        viewHeaderId.setPadding(5, 1, 5, 1);
        viewHeaderBrand.setPadding(5, 1, 5, 1);
        viewHeaderRasa.setPadding(5, 1, 5, 1);
        viewHeaderStok.setPadding(5, 1, 5, 1);
        viewHeaderHarga.setPadding(5, 1, 5, 1);
        viewHeaderAction.setPadding(5, 1, 5, 1);

        tableRow.addView(viewHeaderId);
        tableRow.addView(viewHeaderBrand);
        tableRow.addView(viewHeaderRasa);
        tableRow.addView(viewHeaderStok);
        tableRow.addView(viewHeaderHarga);
        tableRow.addView(viewHeaderAction);

        kopiTable.addView(tableRow, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        try {
            arrayKopi = new JSONArray(kopi.showKopi());

            for (int i = 0; i < arrayKopi.length(); i++){
                JSONObject jsonChildNode = arrayKopi.getJSONObject(i);
                String brand = jsonChildNode.optString("brand");
                String rasa = jsonChildNode.optString("rasa");
                String stok = jsonChildNode.optString("stok");
                String harga = jsonChildNode.optString("harga");
                String id = jsonChildNode.optString("id");

                System.out.println("Brand :" + brand);
                System.out.println("Rasa :" + rasa);
                System.out.println("Stok :" + stok);
                System.out.println("Harga :" + harga);
                System.out.println("ID :" + id);

                tableRow = new TableRow(this);

                if (i % 2 == 0) {
                    tableRow.setBackgroundColor(Color.LTGRAY);
                }

                TextView viewId = new TextView(this);
                viewId.setText(id);
                viewId.setPadding(5, 1, 5, 1);
                tableRow.addView(viewId);

                TextView viewBrand = new TextView(this);
                viewBrand.setText(brand);
                viewBrand.setPadding(5, 1, 5, 1);
                tableRow.addView(viewBrand);

                TextView viewRasa = new TextView(this);
                viewRasa.setText(rasa);
                viewRasa.setPadding(5, 1, 5, 1);
                tableRow.addView(viewRasa);

                TextView viewStok = new TextView(this);
                viewStok.setText(stok);
                viewStok.setPadding(5, 1, 5, 1);
                tableRow.addView(viewStok);

                TextView viewHarga = new TextView(this);
                viewHarga.setText(harga);
                viewHarga.setPadding(5, 1, 5, 1);
                tableRow.addView(viewHarga);

                btnEdit.add(i, new Button(this));
                btnEdit.get(i).setId(Integer.parseInt(id));
                btnEdit.get(i).setTag("Edit");
                btnEdit.get(i).setText("Edit");
                btnEdit.get(i).setOnClickListener(this);
                tableRow.addView(btnEdit.get(i));

                btnDelete.add(i, new Button(this));
                btnDelete.get(i).setId(Integer.parseInt(id));
                btnDelete.get(i).setTag("Delete");
                btnDelete.get(i).setText("Delete");
                btnDelete.get(i).setOnClickListener(this);
                tableRow.addView(btnDelete.get(i));

                kopiTable.addView(tableRow, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.MATCH_PARENT));
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAddItem) {
            addKopi();
        } else {
            for (int i = 0; i < btnEdit.size(); i++){
                if (v.getId() == btnEdit.get(i).getId() && v.getTag().toString().trim().equals("Edit")){
                    int id = btnEdit.get(i).getId();
                    getDataByID(id);
                } else if (v.getId() == btnDelete.get(i).getId() && v.getTag().toString().trim().equals("Delete")){
                    int id = btnDelete.get(i).getId();
                    deletePakaian(id);
                }
            }
        }
    }

    private void addKopi() {
        LinearLayout llinput = new LinearLayout(this);
        llinput.setOrientation(LinearLayout.VERTICAL);

        final EditText editBrand = new EditText(this);
        editBrand.setHint("Brand");
        llinput.addView(editBrand);

        final EditText editRasa = new EditText(this);
        editRasa.setHint("Rasa");
        llinput.addView(editRasa);

        final EditText editStok = new EditText(this);
        editStok.setHint("Stok");
        llinput.addView(editStok);

        final EditText editHarga = new EditText(this);
        editHarga.setHint("Harga");
        llinput.addView(editHarga);

        AlertDialog.Builder builderInsertKopi = new AlertDialog.Builder(this);
        builderInsertKopi.setIcon(R.drawable.ic_edit);
        builderInsertKopi.setTitle("Insert Kopi");
        builderInsertKopi.setView(llinput);
        builderInsertKopi.setPositiveButton("Insert", (dialog, which) -> {
            String brand = editBrand.getText().toString();
            String rasa = editRasa.getText().toString();
            String stok = editStok.getText().toString();
            String harga = editHarga.getText().toString();

            System.out.println("Brand: " + brand + "Rasa: " + rasa + "Stok: " + stok + "Harga: " + harga);

            String report = kopi.addKopi(brand, rasa, stok, harga);
            Toast.makeText(MainActivity.this, report, Toast.LENGTH_SHORT).show();
            finish();
            startActivity(getIntent());
        });

        builderInsertKopi.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builderInsertKopi.show();
    }

    private void getDataByID(int id) {
        String brandEdit = null, rasaEdit = null, stokEdit = null, hargaEdit = null;
        JSONArray arrayPersonal;

        try {
            arrayPersonal = new JSONArray(kopi.getKopiById(id));

            for (int i = 0; i < arrayPersonal.length(); i++){
                JSONObject jsonChildNode = arrayPersonal.getJSONObject(i);
                brandEdit = jsonChildNode.optString("brand");
                rasaEdit = jsonChildNode.optString("rasa");
                stokEdit = jsonChildNode.optString("stok");
                hargaEdit = jsonChildNode.optString("harga");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        final TextView viewId = new TextView(this);
        viewId.setText(String.valueOf(id));
        viewId.setTextColor(Color.TRANSPARENT);
        layoutInput.addView(viewId);

        final EditText editBrand = new EditText(this);
        editBrand.setText(brandEdit);
        layoutInput.addView(editBrand);

        final EditText editRasa = new EditText(this);
        editRasa.setText(rasaEdit);
        layoutInput.addView(editRasa);

        final EditText editStok = new EditText(this);
        editStok.setText(stokEdit);
        layoutInput.addView(editStok);

        final EditText editHarga = new EditText(this);
        editHarga.setText(hargaEdit);
        layoutInput.addView(editHarga);

        AlertDialog.Builder builderEditKopi = new AlertDialog.Builder(this);
        builderEditKopi.setIcon(R.drawable.ic_edit);
        builderEditKopi.setTitle("Update Kopi");
        builderEditKopi.setView(layoutInput);

        builderEditKopi.setPositiveButton("Update", (dialog, which) -> {
            String brand = editBrand.getText().toString();
            String rasa = editRasa.getText().toString();
            String stok = editStok.getText().toString();
            String harga = editHarga.getText().toString();

            System.out.println("Brand: " + brand + "Rasa: " + rasa + "Stok: " + stok + "Harga" + harga);
            String report = kopi.updateKopi(viewId.getText().toString(), editBrand.getText().toString(),
                    editRasa.getText().toString(), editStok.getText().toString(), editHarga.getText().toString());

            Toast.makeText(MainActivity.this, report, Toast.LENGTH_SHORT).show();
            finish();
            startActivity(getIntent());
        });

        builderEditKopi.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builderEditKopi.show();
    }

    public void deletePakaian(int id){
        kopi.deleteKopi(id);
        finish();
        startActivity(getIntent());
    }


}