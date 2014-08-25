package web.recdata.dao;


import web.recdata.model.Entidade;

public interface DAO {
        
        /* Padrões do CRUD */
        /* Cada classe do pacote DAO implementará essa interface.
         * Cabe a cada classe cliente verificar se o objeto de entrada corresponde ao seu tipo próprio,
         * visto que todas as classes estão abstraídas pela interface Entidade. Por essa razão, as funções
         * desse CRUD podem lançar a exceção ClasseInvalidaException - Creditos Eri Jonh
         * */
        
        public void creat(Entidade entidade) ;
        
        public void readById(Entidade entidade) ;
        
        public void update(Entidade entidade);
        
        public void delete(Entidade entidade);
        
}
