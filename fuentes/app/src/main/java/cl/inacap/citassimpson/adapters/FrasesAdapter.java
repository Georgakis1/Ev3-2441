package cl.inacap.citassimpson.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

import cl.inacap.citassimpson.R;
import cl.inacap.citassimpson.dto.Frase;

public class FrasesAdapter extends ArrayAdapter<Frase> {
    private List<Frase> frases;
    private Activity activity;
    public FrasesAdapter(@NonNull Activity context, int resource, @NonNull List<Frase> objects) {
        super(context, resource, objects);
        this.frases = objects;
        this.activity= context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.activity.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.fraseslv,null,true);
        TextView nombreTv = rowView.findViewById(R.id.nombre_lv);
        TextView fraseTv = rowView.findViewById(R.id.frase_lv);
        ImageView imageIv = rowView.findViewById(R.id.imagen_lv);

        nombreTv.setText(frases.get(position).getNombre());
        fraseTv.setText(frases.get(position).getFrase());
        Picasso.get().load(this.frases.get(position).getImagen())
                        .resize(300,300).centerCrop().into(imageIv);

        return rowView;
    }
}
