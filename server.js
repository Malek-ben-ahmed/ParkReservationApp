import express from "express";
import mongoose from "mongoose";
import cors from "cors";

const app = express();
app.use(express.json());
app.use(cors());

mongoose.connect("mongodb://localhost:27017/ParkingBD")
  .then(() => console.log("Connecté à MongoDB"))
  .catch((err) => console.log("Erreur MongoDB :", err));

const signupSchema = new mongoose.Schema({
  username: String,
  email: String,
  phone:String,
  password: String,

});

const User = mongoose.model("User", signupSchema);
app.get("/", (req, res) => {
    res.send("Le serveur fonctionne !");
});

app.post("/Signup", async (req, res) => {
  try {
    const { username, email,phone,password } = req.body;

    const existingUser = await User.findOne({ email });
    if (existingUser) {
      return res.status(400).json({ message: "Email déjà utilisé" });
    }

    const newUser = new User({ username, email,phone,password });
    await newUser.save();

    res.status(201).json({ message: "Utilisateur créé avec succès" });
  } catch (err) {
    res.status(500).json({ message: "Erreur serveur", error: err });
  }
});
const slotSchema = new mongoose.Schema({
  num: Number,
  etat: String
});

const Slot = mongoose.model("Slot", slotSchema);

// Endpoint pour récupérer tous les slots
app.get("/slots", async (req, res) => {
  try {
    const slots = await Slot.find();
    res.status(200).json(slots);
  } catch (err) {
    res.status(500).json({ message: "Erreur serveur", error: err });
  }
});

app.listen(3000, () => console.log(" Serveur démarré sur http://localhost:3000"));
