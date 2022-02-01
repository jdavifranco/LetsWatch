package com.jdavifranco.letswatch.ui.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jdavifranco.letswatch.R
import com.jdavifranco.letswatch.domain.model.Details


@BindingAdapter("visibilityState")
fun bindResponseState(view:View, responseState: ResponseState<Any>?){
    responseState?.let {
        when(it){
            is ResponseState.Loading->{
                view.findViewById<View>(R.id.success_state).visibility = View.GONE
                view.findViewById<View>(R.id.error_state).visibility = View.GONE
                view.findViewById<View>(R.id.loading_state).visibility = View.VISIBLE
            }
            is ResponseState.Error->{
                view.findViewById<View>(R.id.loading_state).visibility = View.GONE
                view.findViewById<View>(R.id.success_state).visibility = View.GONE
                view.findViewById<View>(R.id.error_state).visibility = View.VISIBLE
            }
            is ResponseState.Success->{
                view.findViewById<View>(R.id.loading_state).visibility = View.GONE
                view.findViewById<View>(R.id.error_state).visibility = View.GONE
                view.findViewById<View>(R.id.success_state).visibility = View.VISIBLE
            }
        }

    }

}

@BindingAdapter("voteAverage")
fun bindMovieVote(textView: TextView, voteAverage: Double){
    textView.text = voteAverage.toString()
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
fun bindYearRuntime(textView: TextView, details: Details?){
    details?.let {
        val year = it.date.subSequence(0, 4)
        val runtime = it.runtime
            textView.text = "$year - $runtime min"
    }
}

@BindingAdapter("bindOverview")
fun bindOverview(textView: TextView, details: Details?){
    details?.let {
            textView.text = "Overview: ${it.overview}"

    }
}

@BindingAdapter("bindGenres")
fun bindGenres(textView: TextView, details: Details?){
    details?.let {
        var genres = it.genreLMS.map { it.name }.toString()
        genres = genres.trim('[', ']')
        textView.text = "Genres: "+genres
    }
}