# ABEDSON TV — Android app (TV, phone, tablet)

The complete app is inside `app/src/main/assets/www/` (index.html, config.js,
admin.html, splash.mp4, logo.png). The Android shell wraps it full-screen,
allows http:// IPTV servers, plays the splash WITH sound, handles the TV
remote BACK button, and keeps the screen awake while watching.
Fully responsive: TVs, phones and tablets, any orientation.

## Get the APK — pick ONE path

### Path A — no installs (GitHub cloud build, ~5 minutes)
1. Create a free account at github.com and make a new repository
2. Upload THIS folder's contents to it (drag & drop on github.com works)
3. Open the repository's **Actions** tab → "Build APK" runs automatically
4. When it finishes, download the **ABEDSON-TV-apk** artifact → your .apk is inside

### Path B — Android Studio (~10 minutes once installed)
1. Install Android Studio (free): developer.android.com/studio
2. Open → select this folder → let it sync
3. Menu: Build → Build App Bundle(s)/APK(s) → **Build APK(s)**
4. Click "locate": `app/build/outputs/apk/release/app-release.apk`

## Install on TVs with Downloader
1. Upload the APK to any direct link (your site / GitHub Releases)
2. On the TV: install **Downloader** (AFTVnews), allow it under
   Settings → Apps → Security → Unknown sources
3. Enter your APK URL in Downloader → Install → the app appears in the
   launcher with the ABEDSON banner

## Updating later
Edit `app/src/main/assets/www/` files (or just replace splash.mp4 / logo.png /
config.js), bump `versionCode` in `app/build.gradle`, rebuild, reinstall.
Users keep their saved logins.

## Notes
- 4K / HEVC / MKV playback: the WebView uses the browser engine, so the same
  codec limits as the web version apply. The next upgrade (native ExoPlayer)
  removes them — the UI stays identical.
- Admin panel: open the app's login page → small ⚙ (PIN 1234), or edit
  `config.js` directly before building.
