# MojasTask

### Entities
     **Album** is the top level entity that all other album entites extend
     Event though the **DisplayableAblum** class neither adds or changes nothing from it's parent class, 
     I decided to create it to represent an album that all it does is being displayed by the UI
     
### Data providers
    The herirachy goes like this 
    **RemoteDataSource** -> **Reposotry**
    which is implemented like this
    **RetrofitAlbumApi** -> **RetrofitRemoteDataSource** -> **AlbumRepository**

### UI
     - **MVP** is used for the task because it's simple enough
     - **BaseActivity** that contains the common Activity logic
     - Activities are only holders the view logic is contained in Fragments
     - Even thought **DisplayableViewHolder** and **FilterableViewHolder** share common logic the logic is duplicated since there purposes are not related
    

