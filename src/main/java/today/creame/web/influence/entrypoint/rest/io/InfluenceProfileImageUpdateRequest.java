package today.creame.web.influence.entrypoint.rest.io;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InfluenceProfileImageUpdateRequest {
    private List<Long> createFileResourceIds = new ArrayList<>();
    private List<Long> deleteFileResourceIds = new ArrayList<>();
}
