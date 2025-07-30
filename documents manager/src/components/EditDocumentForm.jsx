import { useState, useEffect } from "react";

function EditDocumentForm({ documentId, onUpdate, onCancel }) {
  const [title, setTitle] = useState("");
  const [expirationDate, setExpirationDate] = useState("");

  useEffect(() => {
    fetch(`http://localhost:8080/document/${documentId}`)
      .then((res) => res.json())
      .then((doc) => {
        setTitle(doc.title);
        setExpirationDate(doc.expirationDate.slice(0, 10)); // pentru input type=date
      });
  }, [documentId]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const updatedDoc = {
      id: documentId,
      title,
      expirationDate,
    };

    try {
      const res = await fetch(`http://localhost:8080/document/update`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(updatedDoc),
      });

      if (res.ok) {
        onUpdate(); // actualizează lista
      } else {
        console.error("Eroare la actualizare document");
      }
    } catch (err) {
      console.error("Eroare rețea:", err);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h3>Editează document</h3>
      <input
        type="text"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
        required
      />
      <input
        type="date"
        value={expirationDate}
        onChange={(e) => setExpirationDate(e.target.value)}
        required
      />
      <button type="submit">Salvează</button>
      <button type="button" onClick={onCancel}>
        Anulează
      </button>
    </form>
  );
}

export default EditDocumentForm;
