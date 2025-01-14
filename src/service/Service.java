package service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import entite.Etudiant;
import entite.EtudiantWhithAcronyme;
import entite.Formation;
import entite.Module;
import repository.EtudiantRepositoryItf;
import repository.FormationRepository;
import repository.ModuleRepositoryItf;
import util.EntityManagerUtil;

/**
 * Composant service. 
 * @author tophe
 * @version 1.0
 *
 *
 */
public class Service implements ServiceItf {
	private FormationRepository formationRepository;
	private EtudiantRepositoryItf etudiantRepository;
	private ModuleRepositoryItf moduleRepository;
	/**
	 * Est initialisé avec les composants dao.
	 * @param formationRepository composant dao Formation
	 * @param etudiantRepository composant dao Etudiant
	 */
	private List<Formation> formations;
	private List<Module> modules;
	
	public Service(FormationRepository formationRepository, EtudiantRepositoryItf etudiantRepository,
			ModuleRepositoryItf moduleRepository) {
		this.formationRepository = formationRepository;
		this.etudiantRepository = etudiantRepository;
		this.moduleRepository = moduleRepository;
	}

	@Override
	public void create(Formation formation) {
		formationRepository.create(formation);
	}
	
	@Override
	public Formation readSansEtudiant(String acronyme) {
		return formationRepository.readSansEtudiant(acronyme);
	}
	
	@Override
	public void modifier(Formation formation) {
		formationRepository.update(formation);
	}
	
	@Override
	public List<String> readAllAcronyme() {
		return formationRepository.readAllAcronyme();
	}

	@Override
	public void supprimerFormation(String acronyme) {
		formationRepository.delete(acronyme);
	}
	
	@Override
	public List<Formation> readAllSansEtudiant() {
		return formationRepository.readAllSansEtudiant();
	}

	public EtudiantWhithAcronyme readEtudiant(Long id) {
		Etudiant etudiant = etudiantRepository.read(id);
		EtudiantWhithAcronyme etudiantWhithAcronyme = new EtudiantWhithAcronyme(etudiant);
		return etudiantWhithAcronyme;
	}
	
	@Override
	public EtudiantWhithAcronyme inscrire(EtudiantWhithAcronyme ewa) {
		Etudiant etudiant = ewa.getEtudiant();
		String acronyme = ewa.getAcronyme(); 
		System.out.println("inscrire acronyme=" + acronyme);
		formationRepository.inscrire(etudiant, acronyme);
		System.out.println("inscrire etudiant=" + etudiant);
		EtudiantWhithAcronyme etudiantWhithAcronyme = new EtudiantWhithAcronyme(etudiant);
		return etudiantWhithAcronyme;
	}
	@Override
	public Etudiant inscrire(Etudiant etudiant, String acronyme) {
		formationRepository.inscrire(etudiant, acronyme);
		return etudiant;
	}
	
	@Override
	public void supprimerEtudiant(Long id, String acronyme) {
		etudiantRepository.delete(id);
		
	}
	@Override
	public void modifier(EtudiantWhithAcronyme etudiantWhithAcronyme) {
		Etudiant etudiant = etudiantWhithAcronyme.getEtudiant();
		Formation formation = formationRepository.readSansEtudiant(etudiantWhithAcronyme.getAcronyme());
		etudiant.setFormation(formation);
		etudiantRepository.update(etudiant);
	}

	@Override
	public List<EtudiantWhithAcronyme> readAllEtudiantWithAcronyme() {
		List<Etudiant> etudiants = etudiantRepository.readAll();
		List<EtudiantWhithAcronyme> etudiantWhithAcronymes = new ArrayList<>();
		for(int i=0; i<etudiants.size(); i++) {
			etudiantWhithAcronymes.add(new EtudiantWhithAcronyme(etudiants.get(i)));
		}
		return etudiantWhithAcronymes;
	}

	@Override
	public List<EtudiantWhithAcronyme> readLikeName(String name) {
		List<Etudiant> etudiants = etudiantRepository.readLikeName(name);
		List<EtudiantWhithAcronyme> etudiantWhithAcronymes = new ArrayList<>();
		for(int i=0; i<etudiants.size(); i++) {
			etudiantWhithAcronymes.add(new EtudiantWhithAcronyme(etudiants.get(i)));
		}
		return etudiantWhithAcronymes;
	}

	@Override
	public Formation readAvecEtudiantEtModule(String acronyme) {
		return formationRepository.readAvecEtudiantEtModule(acronyme);
	}

	@Override
	public void ajouterModuleFormation(Module module, String acronyme) {
		formationRepository.ajouterModuleFormation(module, acronyme);
	}

	@Override
	public List<Module> readAllModule() {
		List<Module> modules = moduleRepository.readAllModule();
		List<Module> modulesList = new ArrayList<>();
		for(int i=0; i<modules.size(); i++) {
			modulesList.add(modules.get(i));
		}
		return modulesList;
	}


	@Override
	public Module update(Module module) {
		return null;
	}

	@Override
	public void supprimerModuleFormation(Module module, String acronyme) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifierModuleFormation(Module module, String acronyme) {
		// TODO Auto-generated method stub
		
	}
}


	

