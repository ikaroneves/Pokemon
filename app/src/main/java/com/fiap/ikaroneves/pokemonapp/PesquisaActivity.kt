package com.fiap.ikaroneves.pokemonapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fiap.ikaroneves.pokemonapp.api.PokemomAPI
import com.fiap.ikaroneves.pokemonapp.model.Pokemom
import kotlinx.android.synthetic.main.activity_pesquisa.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient





class PesquisaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesquisa)

        btPesquisar.setOnClickListener{

            val okhttp = OkHttpClient.Builder()
                        .addNetworkInterceptor(StethoInterceptor())
                        .build()

            val retrofit = Retrofit.Builder()
                    .baseUrl("https://pokeapi.co")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okhttp)
                    .build()



            val api = retrofit.create(PokemomAPI::class.java)

            api.getPokemom(etNumero.text.toString().toInt())
                    .enqueue(object : Callback<Pokemom>{
                        override fun onFailure(call: Call<Pokemom>?, t: Throwable?) {
                            Toast.makeText(this@PesquisaActivity,
                                    t?.message,
                                    Toast.LENGTH_LONG).show()

                        }

                        override fun onResponse(call: Call<Pokemom>?, response: Response<Pokemom>?) {
                            if (response?.isSuccessful == true){
                                    val pokemon = response.body()

                                    tvPokemon.text = pokemon?.nome

                                    Picasso.get()
                                            .load(pokemon?.sprites?.frontDefault)
                                            .placeholder(R.drawable.loading)
                                            .error(R.drawable.notfound).into(ivPokemon)

                            } else {
                                ivPokemon.setImageDrawable(getDrawable(R.drawable.notfound))
                                //Toast.makeText(this@PesquisaActivity,
                                //    "Deu Ruim",
                                //    Toast.LENGTH_LONG).show()
                            }
                        }
                    })

        }
    }
}
