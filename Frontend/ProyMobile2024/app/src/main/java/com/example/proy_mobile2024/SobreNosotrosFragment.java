package com.example.proy_mobile2024;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SobreNosotrosFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        View view = inflater.inflate(R.layout.fragment_sobre_nosotros, container, false);

        // Botón para ver en Google Maps
        Button buttonGoogleMaps = view.findViewById(R.id.button_google_maps);
        buttonGoogleMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intentar abrir Google Maps
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=Veterinaria Alem Sucursal Colón, Córdoba");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                try {
                    // Si Google Maps está instalado, se abrirá
                    startActivity(mapIntent);
                } catch (ActivityNotFoundException e) {
                    // Si Google Maps no está instalado, redirigir al navegador
                    Uri webIntentUri = Uri.parse("https://www.google.com/maps/place/Veterinaria+Alem+Sucursal+Col%C3%B3n/@-31.4022818,-64.2024893,13z/data=!4m14!1m7!3m6!1s0x9432987e1d449115:0x97882a527eaebab1!2sVeterinaria+Alem+Sucursal+Col%C3%B3n!8m2!3d-31.410384!4d-64.1945594!16s%2Fg%2F1tfjrt2k!3m5!1s0x9432987e1d449115:0x97882a527eaebab1!8m2!3d-31.410384!4d-64.1945594!16s%2Fg%2F1tfjrt2k?hl=es-419&entry=ttu");
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, webIntentUri);
                    startActivity(webIntent);
                }
            }
        });

        return view;
    }
}
