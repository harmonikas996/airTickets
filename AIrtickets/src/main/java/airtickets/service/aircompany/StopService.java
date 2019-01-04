package airtickets.service.aircompany;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import airtickets.dto.aircompany.SeatDTO;
import airtickets.dto.aircompany.StopDTO;
import airtickets.model.aircompany.Stop;
import airtickets.repo.aircompany.StopRepository;

@Service
public class StopService {
	
	@Autowired
	private StopRepository stopRepository;
	
	public List<StopDTO> getStops(){
		List<StopDTO> stop = new ArrayList<StopDTO>();
		List<Stop> stopList = stopRepository.findAll();
		
		for(Stop s : stopList) {
			StopDTO seatdto = new StopDTO(s);
			stop.add(seatdto);
		}
		return stop;
	}
	
	public StopDTO getStop(long id) {
		Stop s = stopRepository.findById(id);
		StopDTO stop = new StopDTO(s);
		
		return stop;
	}
	
	public StopDTO addStop(StopDTO stopdto) {
		Stop stop = new Stop(stopdto);
		stopRepository.save(stop);
		stopdto.setId(stop.getId());
		
		return stopdto;
	}
	
	public void deleteStop(long id) {
		stopRepository.deleteById(id);
	}
	
}
