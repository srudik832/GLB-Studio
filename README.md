# GLB Studio - Technical Task

## 🛠 Project Architecture & Logic
The app is built using **MVVM (Model-View-ViewModel)** pattern to ensure a clean separation of concerns.

### 1. Data Layer (Offline First)
* **Room Database**: Used to store metadata for the GLB models.
* **Internal Storage**: 3D models are stored in the app's private internal storage to ensure they are available fully offline without needing SD card permissions.

### 2. Role-Based Flow
* **Admin Role**: Accesses a management dashboard where they can trigger a File Picker to select `.glb` files. These files are then copied to the app's internal directory and their paths are saved in Room.
* **User Role**: A simplified gallery view that fetches the list of available models from the Room database and displays them in a interactive list.

### 3. 3D Rendering Engine
* **SceneView**: I chose SceneView (based on Filament) for its high-performance rendering on Android. The viewer supports 360-degree rotation and zoom gestures.

## 📝 Developer Note & Roadmap
Due to the 48-hour timeframe, the current version focuses on the **Core Architecture** and **UI/UX Design**. While the foundational logic for model management is implemented, I have prioritized the stability of the 3D viewing experience and the Room database integration.

With more time, the next phase would include:
* **Advanced Model Previews**: Generating 2D thumbnails for the GLB list.
* **Cloud Sync**: Optional background syncing when the device returns online.
* **Better UI/UX Design**