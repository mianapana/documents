import { useState } from "react";
import "./AddDocumentForm.css";

function AddDocumentForm() {
  const [title, setTitle] = useState("");
  const [expirationDate, setExpirationDate] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    const newDoc = {
      title,
      expirationDate,
      user: { id: 1 },
      type: { id: 1 },
    };
    try {
      const response = await fetch("http://localhost:8080/document/add", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(newDoc),
      });

      if (response.ok) {
        setTitle("");
        setExpirationDate("");
      } else {
        console.error("Eroare la adăugarea documentului");
      }
    } catch (error) {
      console.error("Eroare rețea:", error);
    }
  };

  return (
    <form className="add-document-form" onSubmit={handleSubmit}>
      <h3>📄 Adaugă un document</h3>

      <div className="form-group">
        <label htmlFor="title">Titlu document:</label>
        <input
          type="text"
          id="title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          placeholder="Ex: Buletin"
          required
        />
      </div>

      <div className="form-group">
        <label htmlFor="expiration">Dată expirare:</label>
        <input
          type="date"
          id="expiration"
          value={expirationDate}
          onChange={(e) => setExpirationDate(e.target.value)}
          required
        />
      </div>

      <button type="submit">💾 Salvează document</button>
    </form>
  );
}
export default AddDocumentForm;
