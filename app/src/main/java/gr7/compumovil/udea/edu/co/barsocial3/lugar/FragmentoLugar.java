package gr7.compumovil.udea.edu.co.barsocial3.lugar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gr7.compumovil.udea.edu.co.barsocial3.R;
import gr7.compumovil.udea.edu.co.barsocial3.evento.EventosFragmento;
import gr7.compumovil.udea.edu.co.barsocial3.producto.ProductoFragment;


/*
Fragmento que contiene la informacion del lugar
 */

public class FragmentoLugar extends Fragment {
    private TabLayout pestanas;
    private AppBarLayout appBar;
    private ViewPager viewPager;
    Bundle bundle;

    public FragmentoLugar() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_paginado, container, false);

        bundle = getArguments();


        if (savedInstanceState == null) {
            insertarTabs(container);
            // Setear adaptador al viewpager.
            viewPager = (ViewPager) view.findViewById(R.id.pager);
            poblarViewPager(viewPager);
            pestanas.setupWithViewPager(viewPager);
        }

        return view;
    }
    private void insertarTabs(ViewGroup container) {
        View padre = (View) container.getParent();
        appBar = (AppBarLayout) padre.findViewById(R.id.appbar);
        pestanas = new TabLayout(getActivity());
        pestanas.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        appBar.addView(pestanas);
    }

    private void poblarViewPager(ViewPager viewPager) {
        AdaptadorSecciones adapter = new AdaptadorSecciones(getFragmentManager());

        Fragment fragmentoLugarInfo = new FragmentoLugarInfo();
        fragmentoLugarInfo.setArguments(bundle);
        adapter.addFragment(fragmentoLugarInfo, getString(R.string.titulo_tab__info_lugar));

        Fragment productoFragment=new ProductoFragment();
        productoFragment.setArguments(bundle);
        adapter.addFragment(productoFragment, getString(R.string.titulo_tab_productos_lugar));

        Fragment eventosFragmento =new EventosFragmento();
        bundle.putString("lugar", "eventos");
        bundle.putBoolean("evento", true);
        eventosFragmento.setArguments(bundle);
        adapter.addFragment(eventosFragmento, getString(R.string.titulo_tab_eventos_lugar));

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        appBar.removeView(pestanas);
    }

    /**
     * Un {@link FragmentStatePagerAdapter} que gestiona las secciones, fragmentos y
     * títulos de las pestañas
     */
    public class AdaptadorSecciones extends FragmentStatePagerAdapter {
        private final List<Fragment> fragmentos = new ArrayList<>();
        private final List<String> titulosFragmentos = new ArrayList<>();

        public AdaptadorSecciones(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return fragmentos.get(position);
        }

        @Override
        public int getCount() {
            return fragmentos.size();
        }

        public void addFragment(android.support.v4.app.Fragment fragment, String title) {
            fragmentos.add(fragment);
            titulosFragmentos.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titulosFragmentos.get(position);
        }
    }


}
