import { useEffect, useState } from "react";
import "./DocumentsList.css";
import EditDocumentForm from "./EditDocumentForm";



function DocumentsList() {


  const [documents, setDocuments] = useState([]);
  const [editingId, setEditingId] = useState(null);
  const [searchQuery, setSearchQuery] = useState("");
  const [sortAsc, setSortAsc] = useState(true);

  useEffect(() => {
    fetch("http://localhost:8080/document/getAll")
      .then((res) => res.json())
      .then((data) => setDocuments(data));
  }, []);

  const refreshList = () => {
    fetch("http://localhost:8080/document/getAll")
      .then((res) => res.json())
      .then((data) => {
        setDocuments(data);
        setEditingId(null);
      });
  };

  const handleDelete = async (id) => {
    const confirmDelete = window.confirm(
      "Ești sigur că vrei să ștergi acest document?"
    );
    if (!confirmDelete) return;

    try {
      const response = await fetch(
        `http://localhost:8080/document/delete/${id}`,
        {
          method: "DELETE",
        }
      );
      console.log(documents);

      if (response.ok) {
        setDocuments((prevDocs) => prevDocs.filter((doc) => doc.id !== id));
      } else {
        console.error("Eroare la ștergerea documentului");
      }
    } catch (error) {
      console.error("Eroare rețea:", error);
    }
  };

  const filteredDocs = documents
    .filter((doc) =>
      doc.title.toLowerCase().includes(searchQuery.toLowerCase())
    )
    .sort((a, b) => {
      const dateA = new Date(a.expirationDate);
      const dateB = new Date(b.expirationDate);
      return sortAsc ? dateA - dateB : dateB - dateA;
    });

  return (
    <div className="document-list">
      <input
        type="text"
        placeholder="Caută după titlu..."
        value={searchQuery}
        onChange={(e) => setSearchQuery(e.target.value)}
        className="search-input"
      />
      <h2>📋 Lista documentelor</h2>
      <button onClick={() => setSortAsc((prev) => !prev)}>
        Sortează după dată {sortAsc ? "🔼" : "🔽"}
      </button>

      {editingId ? (
        <EditDocumentForm
          documentId={editingId}
          onUpdate={refreshList}
          onCancel={() => setEditingId(null)}
        />
      ) : (
        <ul>
          {filteredDocs.map((doc) => {
            const expDate = new Date(doc.expirationDate);
            const now = new Date();
            const daysLeft = Math.ceil((expDate - now) / (1000 * 60 * 60 * 24));

            let status = "";
            if (daysLeft < 0) status = "expirat";
            else if (daysLeft <= 7) status = "aproape de expirare";

            return (
              <li key={doc.id} className="document-item">
                <div className="doc-row">
                  <span className="title">{doc.title}</span>
                  <span className="badge">ID: {doc.id}</span>
                  <span className="expires">
                    Expiră pe:{" "}
                    <strong
                      className={
                        status === "expirat"
                          ? "expired"
                          : status === "aproape de expirare"
                          ? "expiring"
                          : ""
                      }
                    >
                      {expDate.toLocaleDateString("ro-RO")}
                    </strong>
                  </span>
                  {status && (
                    <span
                      className={`status-badge ${
                        status === "expirat" ? "expired" : "expiring"
                      }`}
                    >
                      {status === "expirat"
                        ? "❌ Expirat"
                        : "⚠️ Aproape de expirare"}
                    </span>
                  )}
                  <button
                    className="edit-btn"
                    onClick={() => setEditingId(doc.id)}
                  >
                    ✏️ Editează
                  </button>
                  <button
                    className="delete-btn"
                    onClick={() => handleDelete(doc.id)}
                  >
                    🗑️ Șterge
                  </button>
                </div>
              </li>
            );
          })}
        </ul>
      )}
    </div>
  );
}

export default DocumentsList;
