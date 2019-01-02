package retro.mobpro.prak.myapplicationrecycle;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retro.mobpro.prak.model.Movie;
import retro.mobpro.prak.myapplicationrecycle.MovieAdapter;
import retro.mobpro.prak.model.MovieResponse;
import retro.mobpro.prak.rest.ApiClient;
import retro.mobpro.prak.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    RecyclerView rvMovies;
    private ArrayList<Movie> listMoview = new ArrayList<>();
    ProgressDialog loading;
    ApiInterface apiService;
    MovieAdapter adapter;
    View rootView;

    LinearLayoutManager mLayoutManager;

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_blank, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovies = view.findViewById(R.id.rv_movies);
        adapter = new MovieAdapter(listMoview, getActivity(), 0);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieResponse> call = apiService.getMovieNowPlay(MainActivity.API_KEY, "en-US");
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    listMoview = response.body().getResults();
                    mLayoutManager = new LinearLayoutManager(getContext());
                    rvMovies.setHasFixedSize(true);
                    rvMovies.setLayoutManager(mLayoutManager);
                    rvMovies.setAdapter(new MovieAdapter(listMoview, getContext(), 0));
                    adapter.notifyDataSetChanged();
                } else {
                    loading.dismiss();
                    Toast.makeText(getContext(), "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to Connect Internet !", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
