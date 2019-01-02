package retro.mobpro.prak.myapplicationrecycle;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final static String API_KEY = "fd88576790824d10424d8af542f423ed";
    public final static String baseUrlImage = "http://image.tmdb.org/t/p/w185";
    private final static String lang = "";

    Button btnNowplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = new Bundle();
        BlankFragment fragobj = new BlankFragment();
        fragobj.setArguments(bundle);
        replaceFragment(fragobj);

    }

    /**
     * for replace fragment
     *
     * @param fragment {@link Fragment}
     */
    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_play, fragment)
                .commit();
    }
}
