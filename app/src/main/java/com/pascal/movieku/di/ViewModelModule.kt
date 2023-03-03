package com.pascal.movieku.di

import com.pascal.movieku.base.arch.BaseGenericViewModel
import com.pascal.movieku.data.repository.*
import com.pascal.movieku.presentation.ui.auth.forget.ForgetPasswordViewModel
import com.pascal.movieku.presentation.ui.auth.forget.newpassword.NewPasswordViewModel
import com.pascal.movieku.presentation.ui.auth.login.LoginViewModel
import com.pascal.movieku.presentation.ui.auth.register.RegisterViewModel
import com.pascal.movieku.presentation.ui.homepage.detailmovie.DetailMovieViewModel
import com.pascal.movieku.presentation.ui.homepage.home.HomeViewModel
import com.pascal.movieku.presentation.ui.homepage.profile.edit.EditProfileViewModel
import com.pascal.movieku.presentation.ui.homepage.profile.user.ProfileUserViewModel
import com.pascal.movieku.presentation.ui.boarding.OnBoardingViewModel
import com.pascal.movieku.presentation.ui.homepage.favorite.FavoriteViewModel
import com.pascal.movieku.presentation.ui.splashscreen.SplashScreenViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object ViewModelModule {
    @Provides
    @FragmentScoped
    fun provideSplashScreenViewModel(splashScreenRepository: SplashScreenRepository): SplashScreenViewModel {
        return BaseGenericViewModel(SplashScreenViewModel(splashScreenRepository)).create(
            SplashScreenViewModel::class.java
        )
    }

    @Provides
    @FragmentScoped
    fun provideOnBoardingViewModel(onBoardingRepository: OnBoardingRepository): OnBoardingViewModel {
        return BaseGenericViewModel(OnBoardingViewModel(onBoardingRepository)).create(
            OnBoardingViewModel::class.java
        )
    }

    @Provides
    @FragmentScoped
    fun provideLoginViewModel(loginRepository: LoginRepository): LoginViewModel {
        return BaseGenericViewModel(LoginViewModel(loginRepository)).create(
            LoginViewModel::class.java
        )
    }

    @Provides
    @FragmentScoped
    fun provideRegisterViewModel(registerRepository: RegisterRepository): RegisterViewModel {
        return BaseGenericViewModel(RegisterViewModel(registerRepository)).create(
            RegisterViewModel::class.java
        )
    }

    @Provides
    @FragmentScoped
    fun provideForgetPasswordViewModel(forgetPasswordRepository: ForgetPasswordRepository): ForgetPasswordViewModel {
        return BaseGenericViewModel(ForgetPasswordViewModel(forgetPasswordRepository)).create(
            ForgetPasswordViewModel::class.java
        )
    }

    @Provides
    @FragmentScoped
    fun provideNewPasswordViewModel(newPasswordRepository: NewPasswordRepository): NewPasswordViewModel {
        return BaseGenericViewModel(NewPasswordViewModel(newPasswordRepository)).create(
            NewPasswordViewModel::class.java
        )
    }

            @Provides
    @FragmentScoped
    fun provideHomeViewModel(homepageRepository: HomeRepository): HomeViewModel {
        return BaseGenericViewModel(HomeViewModel(homepageRepository)).create(
            HomeViewModel::class.java
        )
    }

    @Provides
    @FragmentScoped
    fun provideDetailMovieViewModel(detailMovieRepository: DetailMovieRepository): DetailMovieViewModel {
        return BaseGenericViewModel(DetailMovieViewModel(detailMovieRepository)).create(
            DetailMovieViewModel::class.java
        )
    }

    @Provides
    @FragmentScoped
    fun provideFavoriteViewModel(favoriteRepository: FavoriteRepository): FavoriteViewModel {
        return BaseGenericViewModel(FavoriteViewModel(favoriteRepository)).create(
            FavoriteViewModel::class.java
        )
    }

    @Provides
    @FragmentScoped
    fun provideProfileUserViewModel(profileUserRepository: ProfileUserRepository): ProfileUserViewModel {
        return BaseGenericViewModel(ProfileUserViewModel(profileUserRepository)).create(
            ProfileUserViewModel::class.java
        )
    }

    @Provides
    @FragmentScoped
    fun provideEditProfileUser(editProfileRepository: EditProfileRepository): EditProfileViewModel {
        return BaseGenericViewModel(EditProfileViewModel(editProfileRepository)).create(
            EditProfileViewModel::class.java
        )
    }
}