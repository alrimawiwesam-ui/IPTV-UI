/* ═══════════════════════════════════════════════════════════════════
   APP CONFIGURATION — this is the only file you edit by hand.

   These are the DEFAULTS shipped to every user/device.
   The admin page (admin.html) can override them on a single device,
   and can also generate a new copy of this file for you to upload so
   the change applies to everyone.
   ═══════════════════════════════════════════════════════════════════ */
window.APP_CONFIG = {

  /* Branding ------------------------------------------------------ */
  appName:     "ABEDSON TV",          // shown in the title bar and beside the logo
  letter:      "A",                   // fallback badge letter when no logo file is found
  logoImage:   "logo.png",            // logo file next to this file, a full URL, or "" for none

  /* Splash -------------------------------------------------------- */
  splashVideo: "splash.mp4",          // intro video file — replace this file anytime
  splashSound: true,                  // play the intro with sound (guaranteed in the TV app)

  /* Banner preview cycle ------------------------------------------ */
  posterSeconds: 2,                   // how long the poster shows before a scene plays
  sceneSeconds:  10,                  // how long each random scene plays

  /* Server -------------------------------------------------------- */
  defaultHost: "http://iptv.abedson.com:8080",   // never shown to users

  /* Links --------------------------------------------------------- */
  storeUrl:    "https://store.abedson.com/subscribe",  // "Register now" button
  supportUrl:  "https://wa.me/000000000",              // "Forgot password" link

  /* Admin --------------------------------------------------------- */
  adminPin:    "1234"                 // PIN required to open admin.html
};
