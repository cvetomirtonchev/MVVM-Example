package com.example.mvvm.viewmodels;

import com.example.mvvm.model.NewsResponse;
import com.example.mvvm.networking.BaseApiResponse;
import com.example.mvvm.networking.repository.NewsRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by kostadin.georgiev on 7/10/2019.
 */
public class NewsViewModel extends ViewModel {
    private final MediatorLiveData<BaseApiResponse<NewsResponse>> mNewsMutableLiveData;
    private final NewsRepository mNewsRepository;

    public NewsViewModel(NewsRepository newsRepository) {
        mNewsRepository = newsRepository;
        mNewsMutableLiveData = new MediatorLiveData<>();
    }

    public void getNews() {
        if (mNewsMutableLiveData.getValue() == null) {
            mNewsMutableLiveData.addSource(mNewsRepository.getNews(), new Observer<BaseApiResponse<NewsResponse>>() {
                @Override
                public void onChanged(BaseApiResponse<NewsResponse> newsResponse) {
                    mNewsMutableLiveData.setValue(newsResponse);
                }
            });
        }
    }

    public LiveData<BaseApiResponse<NewsResponse>> getNewsLiveData() {
        return mNewsMutableLiveData;
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        private NewsRepository mNewsRepository;

        public Factory() {
            mNewsRepository = NewsRepository.getInstance();
        }

        @SuppressWarnings("unchecked")
        @Override
        public NewsViewModel create(@NonNull Class modelClass) {
            return new NewsViewModel(mNewsRepository);
        }
    }
}
