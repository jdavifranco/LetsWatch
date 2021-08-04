package com.jdavifranco.letswatch.ui

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jdavifranco.letswatch.R
import com.jdavifranco.letswatch.database.Movie



@BindingAdapter("voteAverage")
fun bindMovieVote(textView: TextView, movie: Movie?){
    movie?.let { textView.text = it.voteAverage.toString()}
}
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}


@BindingAdapter("yearAndRuntime")
fun bindYearRuntime(textView: TextView, movie: Movie?){
    movie?.let {
        val year = movie.date.subSequence(0, 4)
        val runtime = if (movie.detalhes == null) null else movie.detalhes?.runtime
        if (runtime == null) {
            textView.text = year
        } else {
            textView.text = "$year - $runtime"
        }
    }
}

@BindingAdapter("bindOverview")
fun bindOverview(textView: TextView, movie:Movie?){
    movie?.let {
        val overview = movie.detalhes?.overview
        if (overview != null) {
            textView.text = "Overview: $overview"
        } else {
            textView.text = ""
        }
    }
}

@BindingAdapter("bindGenres")
fun bindGenres(textView: TextView, movie:Movie?){
    movie?.let {
        textView.text = movie.detalhes?.genres
    }
}