package com.example.animales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.animales.adapters.AnimalAdapter;
import com.example.animales.entity.Animal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txtBuscar)
    public EditText txtBuscar;
    @BindView(R.id.listViewAnimales)
    public ListView listViewAnimales;
    private AnimalAdapter animalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loadInfo();
        buscarOnTextListener();

        listViewAnimales.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, animalAdapter.getItem(i).getSonido());
                mediaPlayer.start();
            }
        });

    }

    private void loadInfo(){
        List<Animal> listaAnimales=new ArrayList<>();
        listaAnimales.add(new Animal(R.drawable.perro,"Perro","Es uno de los animales domésticos más antiguos del mundo y el mejor amigo del hombre.",R.raw.perro));
        listaAnimales.add(new Animal(R.drawable.gato,"Gato","Los gatos domésticos, sea cual sea su raza, son todos miembros de una misma especie, Felis catus, que mantiene una relación con los humanos desde hace mucho tiempo.",R.raw.gato));
        listaAnimales.add(new Animal(R.drawable.buho,"Buho"," Es un género de aves estrigiformes de la familia Strigidae que cuenta con más de veinte especies distribuidas en muchas partes del planeta.",R.raw.buho));
        listaAnimales.add(new Animal(R.drawable.cuervo,"Cuervo"," Es un ave inteligente y extraordinariamente adaptable: vive como carroñero y predador y puede sobrevivir todo el año en lugares tan diferentes como el desierto caluroso o la tundra ártica alta.",R.raw.cuervo));
        listaAnimales.add(new Animal(R.drawable.serpiente,"Serpiente","Caracterizado por la ausencia de patas, colmillos venenosos y el cuerpo muy alargado. ",R.raw.serpiente));
        animalAdapter = new AnimalAdapter(this, listaAnimales);
        listViewAnimales.setAdapter(animalAdapter);
    }

    private void buscarOnTextListener() {
        txtBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                animalAdapter.getFilter().filter(charSequence);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    private void reproducirSonido(){
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.gato);
        mediaPlayer.start();
    }
}